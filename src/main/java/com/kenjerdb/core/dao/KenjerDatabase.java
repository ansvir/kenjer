package com.kenjerdb.core.dao;

import com.kenjerdb.core.parser.writer.DatabaseFileRecordWriter;

import java.io.File;

import static com.kenjerdb.core.constant.KenjerConstant.*;
import static com.kenjerdb.core.dao.PersistencePeriodType.IMMEDIATE;

public class KenjerDatabase {

    /**
     * Database name
     */
    private String name;

    private File database;

    private String rootDirectory;

    private PersistencePeriodType period;

    private String delimiter;

    private String placeholder;

    private KenjerDatabase(
            String name,
            String rootDirectory,
            PersistencePeriodType period,
            String delimiter,
            String placeholderStart,
            String placeholderEnd) {
        this.name = name;
        this.rootDirectory = rootDirectory + "/" + PROJECT_NAME.name() + "/" + name + "/";
        this.database = new File(this.rootDirectory + name + EXT.name());
        this.period = period;
        this.delimiter = delimiter;
        DatabaseFileRecordWriter recordWriter = new DatabaseFileRecordWriter(database);
//        recordWriter.write(period.name());
    }

    public static class Configurer {
        private String name;
        private String rootDirectory;
        private PersistencePeriodType period;
        private String delimiter;
        private String placeholderStart;
        private String placeholderEnd;

        public Configurer() {}

        public Configurer name(String name) {
            this.name = name;
            return this;
        }

        public Configurer directory(String directory) {
            this.rootDirectory = directory;
            return this;
        }

        public Configurer period(PersistencePeriodType period) {
            this.period = period;
            return this;
        }

        public Configurer delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Configurer placeholderStart(String placeholderStart) {
            this.placeholderStart = placeholderStart;
            return this;
        }

        public Configurer placeholderEnd(String placeholderEnd) {
            this.placeholderEnd = placeholderEnd;
            return this;
        }

        public KenjerDatabase configure() {
            if (name == null) {
                name = "database";
            }

            if (period == null) {
                period = IMMEDIATE;
            }

            if (rootDirectory == null) {
                rootDirectory = System.getProperty("user.dir");
            }

            if (delimiter == null) {
                delimiter = DEFAULT_RECORD_DELIMITER.name();
            }

            if (placeholderStart == null) {
                placeholderStart = DEFAULT_PLACEHOLDER_DELIMITER_OPEN.value();
            }

            if (placeholderEnd == null) {
                placeholderEnd = DEFAULT_PLACEHOLDER_DELIMITER_CLOSE.value();
            }

            return new KenjerDatabase(
                    name,
                    rootDirectory,
                    period,
                    delimiter,
                    placeholderStart,
                    placeholderEnd
            );
        }
    }

    public static KenjerDatabase getDefault() {
        return new Configurer()
                .name("example")
                .period(IMMEDIATE)
                .directory(System.getProperty("user.dir"))
                .delimiter(DEFAULT_RECORD_DELIMITER.value())
                .placeholderStart(DEFAULT_PLACEHOLDER_DELIMITER_OPEN.value())
                .placeholderEnd(DEFAULT_PLACEHOLDER_DELIMITER_CLOSE.value())
                .configure();
    }

    protected String getName() {
        return name;
    }

    protected File getDatabase() {
        return database;
    }

    protected String getRootDirectory() {
        return rootDirectory;
    }

    protected PersistencePeriodType getPeriod() {
        return period;
    }
}

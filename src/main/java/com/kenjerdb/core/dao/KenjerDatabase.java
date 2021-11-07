package com.kenjerdb.core.dao;

import com.kenjerdb.core.dao.model.PersistencePeriodType;
import com.kenjerdb.core.exception.DatabaseCreationException;
import com.kenjerdb.core.exception.ValidationException;

import java.io.File;
import java.io.IOException;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.*;
import static com.kenjerdb.core.dao.model.DatabaseFieldsType.*;
import static com.kenjerdb.core.dao.model.PersistencePeriodType.IMMEDIATE;
import static com.kenjerdb.core.util.DelimiterCipher.encryptDelimiter;

public class KenjerDatabase implements FileDatabase {

    /**
     * Database name
     */
    private String name;

    /**
     * Content of the database file
     */
    private File database;

    /**
     * Root directory of the whole databaae
     */
    private String rootDirectory;

    /**
     * Persistence period, see PersistencePeriodType
     */
    private PersistencePeriodType period;

    /**
     * Delimiter of database units
     */
    private String delimiter;

    /**
     * Placeholder start char sequence for database data. See DatabaseFieldsType
     */
    private String placeholderStart;

    /**
     * Placeholder end char sequence for database data. See DatabaseFieldsType
     */
    private String placeholderEnd;

    private KenjerDatabase(
            String name,
            String rootDirectory,
            PersistencePeriodType period,
            String delimiter,
            String placeholderStart,
            String placeholderEnd
    ) throws DatabaseCreationException, ValidationException {
        this.name = name;
        this.rootDirectory = rootDirectory + "/" + PROJECT_NAME.value() + "/" + name + "/";
        this.database = new File(this.rootDirectory + name + EXT.value());
        KenjerFileWriterService fileWriterService = new KenjerFileWriterService();
        try {
            fileWriterService.createDirectory(this.rootDirectory);
            fileWriterService.createFile(database.getAbsolutePath(), true);
        } catch (IOException e) {
            throw new DatabaseCreationException("Database file cannot be created");
        }
        this.period = period;
        this.delimiter = delimiter;
        this.placeholderStart = placeholderStart;
        this.placeholderEnd = placeholderEnd;
        KenjerDatabaseService service = new KenjerDatabaseService(this);
        fileWriterService.append(this.database, this.delimiter);
        service.write(TABLES, "");
        service.write(PERSISTENCE_PERIOD, period.name());
        service.write(INDEX, String.valueOf(0));
        service.write(DELIMITER, encryptDelimiter(this.delimiter));
        service.write(PLACEHOLDER_OPEN, placeholderStart);
        service.write(PLACEHOLDER_CLOSE, placeholderEnd);
    }

    protected KenjerDatabase() {
    }

    public static class Builder {
        private String name;
        private String rootDirectory;
        private PersistencePeriodType period;
        private String delimiter;
        private String placeholderStart;
        private String placeholderEnd;

        public Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder directory(String directory) {
            this.rootDirectory = directory;
            return this;
        }

        public Builder period(PersistencePeriodType period) {
            this.period = period;
            return this;
        }

        public Builder delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder placeholderStart(String placeholderStart) {
            this.placeholderStart = placeholderStart;
            return this;
        }

        public Builder placeholderEnd(String placeholderEnd) {
            this.placeholderEnd = placeholderEnd;
            return this;
        }

        public KenjerDatabase build() throws DatabaseCreationException, ValidationException {
            if (name == null) {
                name = "example";
            }

            if (period == null) {
                period = IMMEDIATE;
            }

            if (rootDirectory == null) {
                rootDirectory = System.getProperty("user.dir");
            }

            if (delimiter == null) {
                delimiter = DEFAULT_RECORD_DELIMITER.value();
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

    public static KenjerDatabase getDefaultDatabase() throws DatabaseCreationException, ValidationException {
        return getDefaultConfig()
                .build();
    }

    public static Builder getDefaultConfig() {
        return new Builder()
                .name("example")
                .period(IMMEDIATE)
                .directory(System.getProperty("user.dir"))
                .delimiter(DEFAULT_RECORD_DELIMITER.value())
                .placeholderStart(DEFAULT_PLACEHOLDER_DELIMITER_OPEN.value())
                .placeholderEnd(DEFAULT_PLACEHOLDER_DELIMITER_CLOSE.value());
    }

    public static KenjerDatabase open(String path) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public File getDatabase() {
        return database;
    }

    @Override
    public String getRootDirectory() {
        return rootDirectory;
    }

    @Override
    public PersistencePeriodType getPeriod() {
        return period;
    }

    @Override
    public String getDelimiter() {
        return this.delimiter;
    }

    @Override
    public String getPlaceholderStart() {
        return placeholderStart;
    }

    @Override
    public String getPlaceholderEnd() {
        return placeholderEnd;
    }

    @Override
    public String toString() {
        return "KenjerDatabase{" +
                "name='" + name + '\'' +
                ", database=" + database +
                ", rootDirectory='" + rootDirectory + '\'' +
                ", period=" + period +
                ", delimiter='" + delimiter + '\'' +
                ", placeholderStart='" + placeholderStart + '\'' +
                ", placeholderEnd='" + placeholderEnd + '\'' +
                '}';
    }
}

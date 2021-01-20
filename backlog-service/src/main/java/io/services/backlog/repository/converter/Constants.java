package io.services.backlog.repository.converter;

public final class Constants {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CREATION_DATE = "creation_date";
    public static final String COLUMN_USER_ID = "user_id";

    public static final class BacklogElementLevel {
        public static final String ELEMENT_LEVEL_TABLE_NAME = "REF_LEVEL";
    }

    public static final class BacklogElementType {
        public static final String ELEMENT_TYPE_TABLE_NAME = "REF_BACKLOG_ELEMENT_TYPE";
    }

    public static final class BacklogElement {
        public static final String ELEMENT_TABLE_NAME = "BACKLOG_ELEMENT";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_METADATA = "metadata";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_TRANSCRIPTION = "transcription";
        public static final String COLUMN_URL = "url";
    }

    public static final class BacklogModule {
        public static final String MODULE_TABLE_NAME = "BACKLOG_MODULE";
        public static final String MODULE_ELEMENT_TABLE_NAME = "BACKLOG_MODULE_ELEMENT";
        public static final String COLUMN_MODULE_ID = "module_id";
        public static final String COLUMN_ELEMENT_ID = "element_id";
        public static final String COLUMN_ELEMENT_ORDER = "element_order";
    }


}

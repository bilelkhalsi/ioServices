package io.services.backlog.model;

public enum BacklogElementTypeEnum {

    AUDIO(1L),
    DOCUMENT(2L),
    IMAGE(3L),
    VIDEO(4L);
    private Long id;

    BacklogElementTypeEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

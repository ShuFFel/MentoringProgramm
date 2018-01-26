package com.instinctools.egor.mentoring.web.factories;

public class SettingService {
    private StorageType currentType;

    public SettingService(final StorageType storageType) {
        this.currentType = storageType;
    }

    public StorageType getCurrentType() {
        return currentType;
    }

    public void setCurrentType(final StorageType currentType) {
        this.currentType = currentType;
    }
}

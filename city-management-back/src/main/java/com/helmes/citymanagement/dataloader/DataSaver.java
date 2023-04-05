package com.helmes.citymanagement.dataloader;

import java.util.List;

public interface DataSaver<T> {
    void save(List<T> list);
}

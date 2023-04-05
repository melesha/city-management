package com.helmes.citymanagement.dataloader;

import java.io.File;
import java.util.List;

public interface DataLoader<M> {

    List<M> load(File file);
}

package io.github.qingmo.database;

import io.github.qingmo.dtos.internal.LocateDatabase;

import java.io.IOException;

/**
 * 从csv中获取数据
 */
public interface LocationParse {
    LocateDatabase dataFromCsv() throws IOException;
}

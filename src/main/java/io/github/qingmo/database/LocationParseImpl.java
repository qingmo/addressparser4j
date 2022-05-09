package io.github.qingmo.database;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.github.qingmo.dtos.internal.LocateDatabase;
import io.github.qingmo.dtos.internal.LocateItemDTO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class LocationParseImpl implements LocationParse {
    private File pcaFile = null;
    CsvSchema schema = CsvSchema.builder()
            .addColumn("country")
            .addColumn("province")
            .addColumn("city")
            .addColumn("area")
            .addColumn("latitude")
            .addColumn("longitude")
            .build();

    public LocationParseImpl(String pcaFileName) {
        if (null == pcaFileName || "" == pcaFileName.trim()) {
            throw new RuntimeException("pca csv file name is empty");
        }
        if (pcaFileName.contains("/")) {
            pcaFile = new File(pcaFileName);
        } else {
            ClassLoader classLoader = getClass().getClassLoader();
            URL url = classLoader.getResource(pcaFileName);
            pcaFile = new File(url.getFile());
        }
    }

    @Override
    public LocateDatabase dataFromCsv() throws IOException {
        final CsvMapper mapper = new CsvMapper();
        MappingIterator<LocateItemDTO> it = mapper
                .readerFor(LocateItemDTO.class)
                .with(schema)
                .readValues(pcaFile);
        List<LocateItemDTO> items = it.readAll();
        return new LocateDatabase(items);
    }
}

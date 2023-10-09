package com.cherif.iheb;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ProductItemReader implements ItemReader<Product> {

    private FlatFileItemReader<Product> reader;

    @Override
    public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("./output.txt"));
        reader.setLinesToSkip(1);

        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"PRODUCT_ID", "NAME", "DESCRIPTION", "PRICE"});

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new ProductFieldSetMapper());

        reader.setLineMapper(lineMapper);
        return reader.read();
    }
}

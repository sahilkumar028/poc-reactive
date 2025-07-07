package com.example.demo.assignment;

import java.io.IOException;
import java.nio.file.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class FileServicePractice implements FileService {
    public static final Logger log= LoggerFactory.getLogger(FileServicePractice.class);
    public static final Path PATH=Path.of("src/main/resources/sec02");
    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(()->Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(()->this.writeFile(fileName,content));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(()->this.deleteFile(fileName));
    }
    private void writeFile(String fileName,String content)  {
        try{
            Files.writeString(PATH.resolve(fileName),content);
            log.info("create {}",fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteFile(String fileName)  {
        try{
            Files.delete(PATH.resolve(fileName));
            log.info("deleted {}",fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

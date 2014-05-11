package jp.gr.java_conf.star_diopside.solo.batch.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jp.gr.java_conf.star_diopside.solo.core.exception.SystemException;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class TemporaryFileListener implements JobExecutionListener {

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Path tempFile;
        try {
            tempFile = Files.createTempFile(null, null);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        jobExecution.getExecutionContext().putString(key, tempFile.toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Path tempFile = Paths.get(jobExecution.getExecutionContext().getString(key));
        try {
            Files.delete(tempFile);
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }
}

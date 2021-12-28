package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public List<File> getFilesWithoutContent(Integer userId) {
        return fileMapper.getFilesWithoutContent(userId);
    }

    public List<String> getFileNames(Integer userId) {
        return fileMapper.getFileNames(userId);
    }

    public Integer insertFile(File file) {
        return fileMapper.insert(file);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }
}

package org.dromara.utils.client;


import org.dromara.utils.entity.FileInfo;
import org.dromara.utils.strategy.StorageContext;
import org.dromara.utils.strategy.StorageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件管理器
 * 支持动态切换存储策略
 */
@Component
public class FileManager {

    @Value("${oss.type:minio}")
    private String defaultOssType;

    @Autowired
    private StorageContext storageContext;

    // 缓存策略实例，避免重复获取
    private final Map<String, StorageStrategy> strategyCache = new ConcurrentHashMap<>();

    /**
     * 根据指定的存储类型获取存储策略
     *
     * @param ossType 存储类型
     * @return 存储策略
     */
    private StorageStrategy getStorageStrategy(String ossType) {
        return strategyCache.computeIfAbsent(ossType,
                type -> {
                    StorageStrategy strategy = storageContext.getStrategy(type + "Strategy");
                    if (strategy == null) {
                        throw new RuntimeException("未找到对应的存储策略: " + type);
                    }
                    return strategy;
                });
    }

    /**
     * 使用默认存储策略上传文件
     *
     * @param file 文件
     * @return 文件名
     */
    public String uploadFile(MultipartFile file) throws Exception {
        return uploadFile(file, defaultOssType);
    }

    /**
     * 使用指定存储策略上传文件
     *
     * @param file    文件
     * @param ossType 存储类型
     * @return 文件名
     */
    public String uploadFile(MultipartFile file, String ossType) throws Exception {
        StorageStrategy strategy = getStorageStrategy(ossType);
        return strategy.uploadFile(file);
    }

    /**
     * 使用默认存储策略下载文件
     *
     * @param fileName 文件名
     * @return 文件流
     */
    public InputStream downloadFile(String fileName) throws Exception {
        return downloadFile(fileName, defaultOssType);
    }

    /**
     * 使用指定存储策略下载文件
     *
     * @param fileName 文件名
     * @param ossType  存储类型
     * @return 文件流
     */
    public InputStream downloadFile(String fileName, String ossType) throws Exception {
        StorageStrategy strategy = getStorageStrategy(ossType);
        return strategy.downloadFile(fileName);
    }

    /**
     * 使用默认存储策略删除文件
     *
     * @param fileName 文件名
     */
    public void deleteFile(String fileName) throws Exception {
        deleteFile(fileName, defaultOssType);
    }

    /**
     * 使用指定存储策略删除文件
     *
     * @param fileName 文件名
     * @param ossType  存储类型
     */
    public void deleteFile(String fileName, String ossType) throws Exception {
        StorageStrategy strategy = getStorageStrategy(ossType);
        strategy.deleteFile(fileName);
    }

    /**
     * 使用默认存储策略获取文件信息
     *
     * @param fileName 文件名
     * @return 文件信息
     */
    public FileInfo getFileInfo(String fileName) throws Exception {
        return getFileInfo(fileName, defaultOssType);
    }

    /**
     * 使用指定存储策略获取文件信息
     *
     * @param fileName 文件名
     * @param ossType  存储类型
     * @return 文件信息
     */
    public FileInfo getFileInfo(String fileName, String ossType) throws Exception {
        StorageStrategy strategy = getStorageStrategy(ossType);
        return strategy.getFileInfo(fileName);
    }

    /**
     * 使用默认存储策略获取文件访问URL
     *
     * @param fileName 文件名
     * @return 文件访问URL
     */
    public String getFileUrl(String fileName) throws Exception {
        return getFileUrl(fileName, defaultOssType);
    }

    /**
     * 使用指定存储策略获取文件访问URL
     *
     * @param fileName 文件名
     * @param ossType  存储类型
     * @return 文件访问URL
     */
    public String getFileUrl(String fileName, String ossType) throws Exception {
        // 这里可以根据不同的存储策略返回对应的文件访问URL
        StorageStrategy strategy = getStorageStrategy(ossType);
        // 可以根据具体策略类型返回不同的URL格式
        return ossType + "/" + fileName;
    }

    /**
     * 检查文件是否存在（使用默认存储策略）
     *
     * @param fileName 文件名
     * @return 是否存在
     */
    public boolean exists(String fileName) {
        return exists(fileName, defaultOssType);
    }

    /**
     * 检查文件是否存在（使用指定存储策略）
     *
     * @param fileName 文件名
     * @param ossType  存储类型
     * @return 是否存在
     */
    public boolean exists(String fileName, String ossType) {
        try {
            getFileInfo(fileName, ossType);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 清除策略缓存
     */
    public void clearStrategyCache() {
        strategyCache.clear();
    }

    // Getters and Setters
    public String getDefaultOssType() {
        return defaultOssType;
    }

    public void setDefaultOssType(String defaultOssType) {
        this.defaultOssType = defaultOssType;
    }
}

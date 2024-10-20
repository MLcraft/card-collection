package com.shizubro.cardcollection.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface BulkDownloadInfoCacheRepository extends CrudRepository<BulkDownloadInfoCache, Long> {
    boolean existsBulkDownloadInfoCacheByUpdatedAt(Instant updatedAt);

    BulkDownloadInfoCache getBulkDownloadInfoCacheByUpdatedAt(Instant updatedAt);
}

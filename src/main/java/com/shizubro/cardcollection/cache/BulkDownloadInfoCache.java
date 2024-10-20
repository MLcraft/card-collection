package com.shizubro.cardcollection.cache;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@RedisHash(value = "BulkDownloadInfoCache", timeToLive = 604800L)
public class BulkDownloadInfoCache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String downloadUri;

    @Indexed
    private Instant updatedAt;
}

package com.getit.ind.purchase.userProfile.repository;

import com.getit.ind.purchase.userProfile.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepository
        extends JpaRepository<UserProfile, UUID> {
}

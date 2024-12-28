package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.service.storage.NcpStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileImageService {
    private final NcpStorageService ncpStorageService;

    public String uploadProfileImage(MultipartFile profileImg){
        return ncpStorageService.imageUpload(profileImg);
    }
    public void deleteProfileImage(String profileImgId){
        ncpStorageService.imageDelete(profileImgId);
    }

}

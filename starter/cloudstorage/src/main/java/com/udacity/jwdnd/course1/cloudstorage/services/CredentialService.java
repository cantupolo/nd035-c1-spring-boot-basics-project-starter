package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentials(Integer userId) {
        List<Credential> credentials = credentialMapper.getCredentials(userId);
        for (Credential credential : credentials) {
            credential.setPasswordText(encryptionService
                    .decryptValue(credential.getPassword(), credential.getKey()));
        }
        return credentials;
    }

    public int saveCredential(Credential credential) {
        credential.setKey(encryptionService.generateKey());
        credential.setPassword(encryptionService
                .encryptValue(credential.getPassword(), credential.getKey()));
        if (credential.getId() != null && credential.getId() > 0) {
            credentialMapper.update(credential);
            return credential.getId();
        } else {
            return credentialMapper.insert(credential);
        }
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }

}

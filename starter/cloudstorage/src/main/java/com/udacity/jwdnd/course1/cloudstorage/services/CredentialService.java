package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredentials(Integer userId) {
        return credentialMapper.getCredentials(userId);
    }

    public int saveCredential(Credential credential) {
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

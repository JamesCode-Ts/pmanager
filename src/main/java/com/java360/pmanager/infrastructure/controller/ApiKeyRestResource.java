package com.java360.pmanager.infrastructure.controller;

import com.java360.pmanager.domain.applicationservice.ApiKeyService;
import com.java360.pmanager.domain.applicationservice.MemberService;
import com.java360.pmanager.domain.document.ApiKey;
import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.infrastructure.dto.ApiKeyDTO;
import com.java360.pmanager.infrastructure.dto.MemberDTO;
import com.java360.pmanager.infrastructure.dto.SaveMemberDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.java360.pmanager.infrastructure.controller.RestConstants.PATH_APIKEYS;
import static com.java360.pmanager.infrastructure.controller.RestConstants.PATH_MEMBERS;

@RestController
@RequestMapping(PATH_APIKEYS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ApiKeyRestResource {

    private final ApiKeyService apiKeyService;

    // Estudar esta estrutura no chatgpt
    @PostMapping
    public ResponseEntity<ApiKeyDTO> createMember() {
        ApiKey apiKey = apiKeyService.createApiKey();

        return ResponseEntity
                .created(URI.create(PATH_APIKEYS + "/" + apiKey.getId()))
                .body(ApiKeyDTO.create(apiKey));

    }
    @PutMapping("{id}/revoke")
    public ResponseEntity<Void> revokeApiKey(@PathVariable("id") String id){
        apiKeyService.revokeApiKey(id);
        return ResponseEntity.noContent().build();
    }
}

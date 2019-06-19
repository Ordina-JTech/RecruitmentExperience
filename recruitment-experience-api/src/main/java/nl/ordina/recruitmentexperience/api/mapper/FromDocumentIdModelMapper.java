package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.DocumentIdModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.DocumentId;
import org.springframework.stereotype.Component;

@Component
public class FromDocumentIdModelMapper implements Mapper<DocumentIdModel, DocumentId> {

    @Override
    public DocumentId map(DocumentIdModel input) {
        return DocumentId.builder()
                .id(input.getId())
                .applicationId(input.getApplicationId())
                .creationDate(input.getCreationDate())
                .title(input.getTitle())
                .path(input.getPath())
                .build();
    }
}

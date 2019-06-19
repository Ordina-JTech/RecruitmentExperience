package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Document;
import nl.ordina.recruitmentexperience.data.application.model.DocumentEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromDocumentEntityMapper implements Mapper<DocumentEntity, Document> {

    private final FromApplicationEntityMapper fromApplicationEntityMapper;

    @Override
    public Document map(DocumentEntity input) {
        return Document.builder()
                .id(input.getId())
                .application(fromApplicationEntityMapper.mapNullSafe(input.getApplication()))
                .creationDate(input.getCreationDate())
                .title(input.getTitle())
                .build();
    }
}

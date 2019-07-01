package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.NoteIdModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.NoteId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromNoteIdModelMapper implements Mapper<NoteIdModel, NoteId> {

    private final FromApplicationModelMapper fromApplicationModelMapper;

    @Override
    public NoteId map(NoteIdModel input) {
        return NoteId.builder()
                .id(input.getId())
                .creationDate(input.getCreationDate())
                .author(input.getAuthor())
                .text(input.getText())
                .title(input.getTitle())
                .applicationId(input.getApplicationId())
                .build();
    }
}

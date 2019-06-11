package nl.ordina.recruitmentexperience.core.application.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.application.model.Note;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromNoteEntityMapper implements Mapper<NoteEntity, Note> {

    private final FromApplicationEntityMapper fromApplicationEntityMapper;

    @Override
    public Note map(NoteEntity input) {
        return Note.builder()
                .id(input.getId())
                .application(fromApplicationEntityMapper.map(input.getApplication()))
                .author(input.getAuthor())
                .title(input.getTitle())
                .comments(input.getComments())
                .build();
    }
}

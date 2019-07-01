package nl.ordina.recruitmentexperience.core.mapper;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Note;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import org.springframework.stereotype.Component;

@Component
public class ToNoteEntityMapper implements Mapper<Note, NoteEntity> {

    @Override
    public NoteEntity map(Note input) {
        return null;
    }
}

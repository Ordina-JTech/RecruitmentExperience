package nl.ordina.recruitmentexperience.api.application.mapper;

import nl.ordina.recruitmentexperience.api.application.model.NoteModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.application.model.Note;
import org.springframework.stereotype.Component;

@Component
public class ToNoteModelMapper implements Mapper<Note, NoteModel> {

    @Override
    public NoteModel map(Note input) {
        final NoteModel noteModel = new NoteModel();
        noteModel.setId(input.getId());
        noteModel.setAuthor(input.getAuthor());
        noteModel.setTitle(input.getTitle());
        noteModel.setComments(input.getComments());
        return noteModel;
    }
}

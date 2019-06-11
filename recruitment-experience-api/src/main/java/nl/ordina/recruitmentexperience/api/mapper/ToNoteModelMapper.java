package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.NoteModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Note;
import org.springframework.stereotype.Component;

@Component
public class ToNoteModelMapper implements Mapper<Note, NoteModel> {

    @Override
    public NoteModel map(Note input) {
        final NoteModel noteModel = new NoteModel();
        noteModel.setId(input.getId());
        noteModel.setAuthor(input.getAuthor());
        noteModel.setTitle(input.getTitle());
        noteModel.setText(input.getText());
        return noteModel;
    }
}

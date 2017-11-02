package businesslogic;

import dataaccess.NoteDB;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        this.noteDB = new NoteDB();
    }

    public Note get(int noteId) throws Exception {
        return noteDB.getNoteId(noteId);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int noteId, String contents) throws NotesDBException {
        Note note = new Note(noteId);
        note.setDateCreated(new Date());
        note.setContents(contents);
        return noteDB.update(note);
    }

    public int delete(int noteId) throws Exception {
        Note note = noteDB.getNoteId(noteId);
        return noteDB.delete(note);
    }

    public int insert(String contents) throws NotesDBException {
        //Date today = (Date) new java.util.Date();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        Note note = new Note(0, sqlDate, contents);

        return noteDB.insert(note);
    }
}

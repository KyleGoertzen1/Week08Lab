package servlets;

import businesslogic.NoteService;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NoteService ns = new NoteService();
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            int selectedNoteId = Integer.parseInt(request.getParameter("selectedNoteId"));
            try {
                Note note = ns.get(selectedNoteId);
                request.setAttribute("selectedId", note);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Note> notes = null;
        try {
            notes = ns.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        NoteService ns = new NoteService();

        switch (action) {
            case "delete":
                int selectedNoteId = Integer.parseInt(request.getParameter("selectedNoteId"));
                 {
                    try {
                        ns.delete(selectedNoteId);
                    } catch (Exception ex) {
                        Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "edit": {

                java.util.Date udate = new java.util.Date();
                java.sql.Date newDate = new java.sql.Date(udate.getTime());

                String newContent = request.getParameter("content");
                int newId = Integer.parseInt(request.getParameter("noteId"));
                try {
                    ns.update(newId, newContent);
                } catch (NotesDBException ex) {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "add": {
                String newContent = request.getParameter("content");
                try {
                    ns.insert(newContent);
                } catch (Exception ex) {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            default:
                break;
        }

        List<Note> notes = null;
        try {
            notes = ns.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
}

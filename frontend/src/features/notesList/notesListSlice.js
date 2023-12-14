import { createSlice } from "@reduxjs/toolkit";

import notes from "../../notesData";
import axios from "axios";

const initialState = {
  mainNotes: [...notes],
  archiveNotes: [],
  trashNotes: [],
  editNote: null,
};

const notesListSlice = createSlice({
  name: "notesList",
  initialState,
  reducers: {
    setMainNotes:  (state, { payload }) => {
      // if (state.mainNotes.find(({ id }) => id === payload.id)) {
      //   state.mainNotes = state.mainNotes.map((note) =>
      //     note.id === payload.id ? payload : note
      //   );
      // } else {
      //   state.mainNotes.push(payload);
      // }
      
      for(let i = 0; i < payload.length; i++){
        console.log(JSON.stringify(payload[i]));
        if (state.mainNotes.find(({ id }) => id === payload[i].id)) {
            state.mainNotes = state.mainNotes.map((note) =>
              note.id === payload[i].id ? payload[i] : note
            );
          } 
          else
            state.mainNotes.push(payload[i]);
          
      }
      console.log('MainNotes: ' + JSON.stringify(state.mainNotes));
    },

    setArchiveNotes: (state, { payload }) => {
      state.mainNotes = state.mainNotes.filter(({ id }) => id !== payload.id);
      state.archiveNotes.push({ ...payload, isPinned: false });
    },

    setTrashNotes: (state, { payload }) => {
      state.mainNotes = state.mainNotes.filter(({ id }) => id !== payload.id);
      state.archiveNotes = state.archiveNotes.filter(
        ({ id }) => id !== payload.id
      );
      state.trashNotes.push({ ...payload, isPinned: false });

      // delete in db also
      // try{
      //   const response = axios.delete('http://localhost:8085/api/deletenote/' + payload.id);
      // }catch(err){
      //   console.error('Error deleting note:', err.message);
      // }

    },

    unarchiveNote: (state, { payload }) => {
      state.archiveNotes = state.archiveNotes.filter(
        ({ id }) => id !== payload.id
      );
      state.mainNotes.push(payload);
    },

    restoreNote: (state, { payload }) => {
      state.trashNotes = state.trashNotes.filter(({ id }) => id !== payload.id);
      state.mainNotes.push(payload);
    },
    deleteNote: (state, { payload }) => {
      state.trashNotes = state.trashNotes.filter(({ id }) => id !== payload.id);
    },

    setPinnedNotes: (state, { payload }) => {
      state.mainNotes = state.mainNotes.map((note) =>
        note.id === payload.id ? { ...note, isPinned: !note.isPinned } : note
      );
    },
    setEditNote: (state, { payload }) => {
      state.editNote = payload;
      console.log(state.mainNotes);
    },
    readNote: (state, { payload }) => {
      const { type, id } = payload;

      const setRead = (notes) => {
        state[notes] = state[notes].map((note) =>
          note.id === id ? { ...note, isRead: !note.isRead } : note
        );
      };

      if (type === "archive") {
        setRead("archiveNotes");
      } else if (type === "trash") {
        setRead("trashNotes");
      } else {
        setRead("mainNotes");
      }
    },
    removeTags: (state, { payload }) => {
      state.mainNotes = state.mainNotes.map((note) => ({
        ...note,
        tags: note.tags.filter(({ tag }) => tag !== payload.tag),
      }));
    },
  },
});

export const {
  setMainNotes,
  setArchiveNotes,
  setTrashNotes,
  unarchiveNote,
  restoreNote,
  deleteNote,
  setPinnedNotes,
  setEditNote,
  readNote,
  removeTags,
} = notesListSlice.actions;

export default notesListSlice.reducer;

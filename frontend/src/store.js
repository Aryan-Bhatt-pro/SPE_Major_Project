import { configureStore } from "@reduxjs/toolkit";

//reducers
import {
  menuReducer,
  modalReducer,
  tagsReducer,
  notesListReducer,
} from "./features";

const authReducer = (state = { token: null }, action) => {
  switch (action.type) {
    case 'SET_AUTH_TOKEN':
      return { ...state, token: action.payload };
    default:
      return state;
  }
};

export const store = configureStore({
  reducer: {
    menu: menuReducer,
    modal: modalReducer,
    tags: tagsReducer,
    notesList: notesListReducer,
    auth: authReducer
  },
});

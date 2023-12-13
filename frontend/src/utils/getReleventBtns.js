import { NotesIconBox } from "../styles/styles";

//icons
import { FaTrash, FaArchive, FaTrashRestore, FaEdit } from "react-icons/fa";
import { RiInboxUnarchiveFill } from "react-icons/ri";
import axios from "axios";
//redux
import {
  setArchiveNotes,
  setTrashNotes,
  unarchiveNote,
  restoreNote,
  deleteNote,
  setEditNote,
  toggleCreateNoteModal,
} from "../features";

const getReleventBtns = (type, note, dispatch) => {
  const clickHandler = () => {
    // here we edit the note trigger
    dispatch(setEditNote(note));
    dispatch(toggleCreateNoteModal(true));
  };

  if (type === "archive") {
    return (
      <>
        <NotesIconBox
          onClick={() => dispatch(unarchiveNote(note))}
          data-info="Unarchive"
        >
          <RiInboxUnarchiveFill style={{ fontSize: "1rem" }} />
        </NotesIconBox>
        <NotesIconBox
          onClick={() => dispatch(setTrashNotes(note))}
          data-info="Delete"
        >
          <FaTrash />
        </NotesIconBox>
      </>
    );
  } else if (type === "trash") {
    return (
      <>
        <NotesIconBox
          onClick={() => dispatch(restoreNote(note))}
          data-info="Restore"
        >
          <FaTrashRestore />
        </NotesIconBox>
        <NotesIconBox
          onClick={() => dispatch(deleteNote(note))}
          data-info="Delete"
        >
          <FaTrash />
        </NotesIconBox>
      </>
    );
  } else {
    return (
      <>
        <NotesIconBox data-info="Edit">
          <FaEdit style={{ fontSize: "1.5rem" }} onClick={clickHandler} />
        </NotesIconBox>
        <NotesIconBox
          onClick={() => dispatch(setArchiveNotes(note))}
          data-info="Archive"
        >
          <FaArchive />
        </NotesIconBox>
        <NotesIconBox
          onClick={async () => 
            {try{
              const response = await axios.delete('http://localhost:8085/api/deletenote/' + String(note.id));
            }catch(err){
              console.log(note)
              console.error('Error deleting note:', err.message);
            }
            dispatch(setTrashNotes(note))
          }
            }
          data-info="Delete"
        >
          <FaTrash />
        </NotesIconBox>
      </>
    );
  }
};

export default getReleventBtns;

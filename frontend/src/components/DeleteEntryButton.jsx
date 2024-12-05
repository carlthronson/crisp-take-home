import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useMutation, gql } from '@apollo/client';

const DELETE_ENTRY = gql`
  mutation DeleteEntry($id: ID!) {
    deleteEntry(id: $id) {
      id
      name
      amount
      category
      account
    }
  }
`;

const DeleteEntryForm = ({entry}) => {
    const [deleteEntry] = useMutation(DELETE_ENTRY, {
      });
    
    const [formData, setFormData] = useState({
    id: entry.id,
    name: entry.name,
    amount: entry.amount,
    category: entry.category,
    account: entry.account
  });

  const handleSubmit = async (e) => {
    console.log(JSON.stringify(formData));
    await deleteEntry({ variables: formData });
  };

  return (
    <Popup trigger={<button>Delete</button>} modal>
      {close => (
        <div className="modal">
          <button className="close" onClick={close}>
            &times;
          </button>
          <h2>Confirm delete entry {entry.name}</h2>
          <form onSubmit={handleSubmit}>
            <button type="submit">Submit</button>
          </form>
        </div>
      )}
    </Popup>
  );
};

export default DeleteEntryForm;
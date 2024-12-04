import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useQuery, useMutation, gql } from '@apollo/client';

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
        // refetchQueries: [{ query: GET_ENTRIES }],
      });
    
    const [formData, setFormData] = useState({
    id: entry.id,
    name: entry.name,
    amount: entry.amount,
    category: entry.category,
    account: entry.account
  });

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    console.log(JSON.stringify(formData));
    await deleteEntry({ variables: formData });
    // setNewEntry({ name: '', account: '', amount: 0, category: '' });
    // e.preventDefault();
    // try {
    //   // Send data to your API endpoint
    //   const response = await fetch('/api/create-object', {
    //     method: 'POST',
    //     headers: { 'Content-Type': 'application/json' },
    //     body: JSON.stringify(formData),
    //   });
    //   if (response.ok) {
    //     console.log('Object created successfully');
    //     // Close popup or show success message
    //   }
    // } catch (error) {
    //   console.error('Error creating object:', error);
    // }
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
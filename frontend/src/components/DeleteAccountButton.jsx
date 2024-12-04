import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useQuery, useMutation, gql } from '@apollo/client';

const DELETE_ACCOUNT = gql`
  mutation DeleteAccount($id: ID!) {
    deleteAccount(id: $id) {
      id
      name
      label
      accountTypeName
    }
  }
`;

const DeleteAccountForm = ({account}) => {
    const [deleteAccount] = useMutation(DELETE_ACCOUNT, {
        // refetchQueries: [{ query: GET_ENTRIES }],
      });
    
    const [formData, setFormData] = useState({
    id: account.id,
    name: account.name,
    label: account.label,
    accountTypeName: account.accountTypeName
  });

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    console.log(JSON.stringify(formData));
    await deleteAccount({ variables: formData });
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
          <h2>Confirm delete account {account.name}</h2>
          <form onSubmit={handleSubmit}>
            <button type="submit">Submit</button>
          </form>
        </div>
      )}
    </Popup>
  );
};

export default DeleteAccountForm;
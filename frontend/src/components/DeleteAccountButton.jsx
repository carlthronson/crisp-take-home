import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useMutation, gql } from '@apollo/client';

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
      });
    
    const [formData, setFormData] = useState({
    id: account.id,
    name: account.name,
    label: account.label,
    accountTypeName: account.accountTypeName
  });

  const handleSubmit = async (e) => {
    console.log(JSON.stringify(formData));
    await deleteAccount({ variables: formData });
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
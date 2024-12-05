import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useMutation, gql } from '@apollo/client';

const UPDATE_ACCOUNT = gql`
  mutation UpdateAccount($id: ID!, $name: String!, $label: String!, $accountTypeName: String!) {
    updateAccount(id: $id, name: $name, label: $label, accountTypeName: $accountTypeName) {
      id
      name
      label
      accountTypeName
    }
  }
`;

const UpdateAccountForm = ({account}) => {
    const [updateAccount] = useMutation(UPDATE_ACCOUNT, {
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
    await updateAccount({ variables: formData });
  };

  return (
    <Popup trigger={<button>Update</button>} modal>
      {close => (
        <div className="modal">
          <button className="close" onClick={close}>
            &times;
          </button>
          <h2>Update account {account.accountTypeName}</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
              placeholder="Account Name"
            />
            <input
              type="text"
              name="label"
              value={formData.label}
              onChange={handleInputChange}
              placeholder="Account ID"
            />
            <button type="submit">Submit</button>
          </form>
        </div>
      )}
    </Popup>
  );
};

export default UpdateAccountForm;
import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useMutation, gql } from '@apollo/client';

const CREATE_ACCOUNT = gql`
  mutation CreateAccount($name: String!, $label: String!, $accountTypeName: String!) {
    createAccount(name: $name, label: $label, accountTypeName: $accountTypeName) {
      id
      name
      label
      accountTypeName
    }
  }
`;

const CreateAccountForm = ({accountType}) => {
    const [createAccount] = useMutation(CREATE_ACCOUNT, {
      });
    
    const [formData, setFormData] = useState({
    name: '',
    label: '',
    accountTypeName: accountType
  });

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    console.log(JSON.stringify(formData));
    await createAccount({ variables: formData });
  };

  return (
    <Popup trigger={<button>Create Account</button>} modal>
      {close => (
        <div className="modal">
          <button className="close" onClick={close}>
            &times;
          </button>
          <h2>Create a new account in {accountType}</h2>
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

export default CreateAccountForm;
import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useMutation, gql } from '@apollo/client';

const CREATE_ENTRY = gql`
  mutation CreateEntry($name: String!, $amount: BigDecimal!, $category: String!, $account: String!) {
    createEntry(name: $name, amount: $amount, category: $category, account: $account) {
      id
      name
      amount
      category
      account
    }
  }
`;

const CreateEntryForm = ({account}) => {
    const [createEntry] = useMutation(CREATE_ENTRY, {
      });
    
    const [formData, setFormData] = useState({
    name: '',
    amount: 0,
    category: '',
    account: account.name
  });

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    console.log(JSON.stringify(formData));
    await createEntry({ variables: formData });
  };

  return (
    <Popup trigger={<button>Create Entry</button>} modal>
      {close => (
        <div className="modal">
          <button className="close" onClick={close}>
            &times;
          </button>
          <h2>Create a new entry in {account.name}</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
              placeholder="Name"
            />
            <input
              type="text"
              name="amount"
              value={formData.amount}
              onChange={handleInputChange}
              placeholder="Amount"
            />
            <input
              type="text"
              name="category"
              value={formData.category}
              onChange={handleInputChange}
              placeholder="Category"
            />
            <button type="submit">Submit</button>
          </form>
        </div>
      )}
    </Popup>
  );
};

export default CreateEntryForm;
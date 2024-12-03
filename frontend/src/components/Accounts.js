import React, { useState } from 'react';
import { useQuery, useMutation, gql } from '@apollo/client';

const GET_ACCOUNTS = gql`
  query GetAccounts {
    accounts {
      id
      name
      label
      accountTypeName
    }
  }
`;

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

const DELETE_ACCOUNT = gql`
  mutation DeleteAccount($id: ID!) {
    deleteAccount(id: $id) {
      id
    }
  }
`;

function Accounts() {
  const [newAccount, setNewAccount] = useState({ name: '', label: '', accountTypeName: '' });
  const { loading, error, data } = useQuery(GET_ACCOUNTS);
  const [createAccount] = useMutation(CREATE_ACCOUNT, {
    refetchQueries: [{ query: GET_ACCOUNTS }],
  });
  const [updateAccount] = useMutation(UPDATE_ACCOUNT);
  const [deleteAccount] = useMutation(DELETE_ACCOUNT, {
    refetchQueries: [{ query: GET_ACCOUNTS }],
  });

  const handleCreateAccount = async () => {
    await createAccount({ variables: newAccount });
    setNewAccount({ name: '', label: '', accountTypeName: '' });
  };

  const handleUpdateAccount = async (id, name, label, accountTypeName) => {
    await updateAccount({ variables: { id, name, label, accountTypeName } });
  };

  const handleDeleteAccount = async (id) => {
    await deleteAccount({ variables: { id } });
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      <h2>Accounts</h2>
      <input
        value={newAccount.name}
        onChange={(e) => setNewAccount({ ...newAccount, name: e.target.value })}
        placeholder="Account Name"
      />
      <input
        value={newAccount.label}
        onChange={(e) => setNewAccount({ ...newAccount, label: e.target.value })}
        placeholder="Account Label"
      />
      <input
        value={newAccount.accountTypeName}
        onChange={(e) => setNewAccount({ ...newAccount, accountTypeName: e.target.value })}
        placeholder="Account Type"
      />
      <button onClick={handleCreateAccount}>Create Account</button>

      {data.accounts.map(account => (
        <div key={account.id}>
          <span>{account.name} - {account.label} - {account.accountTypeName}</span>
          <button onClick={() => handleUpdateAccount(account.id, `Updated ${account.name}`, account.label, account.accountTypeName)}>Update</button>
          <button onClick={() => handleDeleteAccount(account.id)}>Delete</button>
        </div>
      ))}
    </div>
  );
}

export default Accounts;
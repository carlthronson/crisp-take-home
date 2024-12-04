import styled from 'styled-components';
import Account from './Account';
import CreateAccountForm from './CreateAccountForm';

const TypeArea = styled.div`
    background-color: #f4f5f7;
    border-radius: 2.5px;
    height: 475px;
    overflow-y: scroll;
    -ms-overflow-style: none;
    scrollbar-width: none;
    border: 1px solid gray;
    flex: 1;
`   ;

const TypeName = styled.h3`
    padding: 8px;
    background-color: lightblue;
    text-align: center;
    position: 'stick',
`   ;

const AccountListArea = styled.div`
    padding: 3px;
  transistion: background-color 0.2s ease;
  background-color: #f4f5f7;
    flex-grow: 1;
    min-height: 100px;
`   ;

export default function TypeColumn({ accountType, accounts, entries, id }) {

    return (
        <TypeArea>
            <TypeName>
                {accountType} <CreateAccountForm accountType={accountType}/>
            </TypeName>
            <AccountListArea>
                {accounts.map((item, index) => (
                    item.accountTypeName === accountType &&
                    <Account key={index} index={index} account={item} total={accounts.length} entries={entries}/>                    
                ))}
            </AccountListArea>
        </TypeArea>
    );
}
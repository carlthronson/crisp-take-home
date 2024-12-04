import styled from 'styled-components';
import TypeColumn from './TypeColumn.jsx';

const OverviewArea = styled.div`
  display: flex;
  flex-direction: row;
`;

export default function Overview({ accountTypes, accounts, entries }) {

  return (
    <OverviewArea>
      {accountTypes.map((accountType, index) => (
        <TypeColumn key={index} id={index} accountType={accountType} accounts={accounts} entries={entries}></TypeColumn>
      ))}
    </OverviewArea>
  );
}

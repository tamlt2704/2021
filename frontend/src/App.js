import logo from './logo.svg';
import './App.css';
import {useState, useEffect} from 'react';

function App() {
    const [users, setUsers] = useState([]);
    useEffect(() => {
        fetch('/users')
            .then(response => response.json())
            .then((result) => {
                console.log(result);
                setUsers(result);
            });
    });
  return (
    <div className="App">
      <ul>
          {users.map(item => (
            <li key={item.id}>
              {item.id} {item.name}
            </li>
          ))}
        </ul>
    </div>
  );
}

export default App;

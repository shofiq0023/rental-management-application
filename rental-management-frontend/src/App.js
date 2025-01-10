
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './pages/Login/Login';
import Register from './pages/Register/Register';
import Home from './pages/Home/Home';

function App() {
  return (
    <BrowserRouter>

    <Routes>
      <Route exact path='/' element={<Login/>}></Route>
      <Route exact path='/login' element={<Login/>}></Route>
      <Route exact path='/register' element={<Register/>}></Route>
      <Route exact path='/home' element={<Home/>}></Route>
    
    </Routes>
    </BrowserRouter>
  );
}

export default App;

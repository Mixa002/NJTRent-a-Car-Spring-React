import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import api from '../api/axios.js';
import { User, ShieldCheck} from "lucide-react";

const Login = () =>{
    const [role, setRole] = useState('KLIJENT');
    const [email,setEmail] = useState('');
    const [password,setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            // Šaljemo email, lozinku i ulogu backendu
            const response = await api.post('/auth/login', { email, password, role });

            // Čuvamo podatke u local storage
            localStorage.setItem('user', JSON.stringify(response.data));

            // Preusmeravanje na osnovu uloge
            if (role === 'RADNIK') navigate('/admin-panel');
            else navigate('/klijent-panel');

        } catch (error) {
            if (error.response?.status === 401) {
                alert("Pogrešan email ili lozinka");
            } else {
                alert("Greška na serveru");
            }
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4">
            <div className="max-w-md w-full space-y-8 p-10 bg-white rounded-xl shadow-lg">
                <div className="text-center">
                    <h2 className="text-3xl font-extrabold text-gray-900">Rent-a-Car</h2>
                    <p className="mt-2 text-sm text-gray-600">Prijavite se na svoj nalog</p>
                </div>

                {/* Dugmići za izbor uloge */}
                <div className="flex p-1 bg-gray-100 rounded-lg">
                    <button
                        onClick={() => setRole('KLIJENT')}
                        className={`flex-1 flex items-center justify-center py-2 px-4 rounded-md transition ${role === 'KLIJENT' ? 'bg-white shadow text-blue-600' : 'text-gray-500'}`}
                    >
                        <User className="w-4 h-4 mr-2" /> Klijent
                    </button>
                    <button
                        onClick={() => setRole('RADNIK')}
                        className={`flex-1 flex items-center justify-center py-2 px-4 rounded-md transition ${role === 'RADNIK' ? 'bg-white shadow text-blue-600' : 'text-gray-500'}`}
                    >
                        <ShieldCheck className="w-4 h-4 mr-2" /> Radnik
                    </button>
                </div>

                <form className="mt-8 space-y-6" onSubmit={handleLogin}>
                    <div className="rounded-md shadow-sm space-y-4">
                        <input
                            type="email"
                            required
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            placeholder="Email adresa"
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <input
                            type="password"
                            required
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            placeholder="Lozinka"
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>

                    <button
                        type="submit"
                        className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700 font-medium"
                    >
                        Prijavi se kao {role.toLowerCase()}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default Login;



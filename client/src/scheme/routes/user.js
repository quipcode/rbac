// App Imports
import Login from '../../modules/core/user/Login'
import Signup from '../../modules/core/user/Signup'
import Profile from '../../modules/core/user/Profile'
import Dashboard from '../../modules/core/user/Dashboard'

// Pages routes
export default {
    login: {
        path: '/user/login',
        component: Login
    },

    signup: {
        path: '/user/signup',
        component: Signup
    },

    profile: {
        path: '/user/profile',
        component: Profile,
        auth: true
    },

    dashboard: {
        path: '/user/dashboard',
        component: Dashboard,
        auth: true
    }
}
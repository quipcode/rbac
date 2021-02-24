// Imports
import React from 'react'
import { Redirect } from 'react-router-dom'
import { useSelector } from "react-redux"

// App Imports
import params from '../../../scheme/config/params.json'
import routes from '../../../scheme/routes'

// Component
const AuthCheck = () => {
    const { isAuthenticated, details } = useSelector(state => state.auth)

    return (
        isAuthenticated
            ? details.role === params.user.roles.admin.key
                ? <Redirect to={routes.adminDashboard.path} />
                : <Redirect to={routes.dashboard.path} />
            : ''
    )
}

export default AuthCheck
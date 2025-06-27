let profileService;

class ProfileService
{
    loadProfile()
    {
        const url = `${config.baseUrl}/profile`;

        // Since axios.defaults.headers.common is already set in user-service.js, 
        // we don't need to manually add headers here
        axios.get(url)
             .then(response => {
                 console.log('Profile data received:', response.data);
                 templateBuilder.build("profile", response.data, "main")
             })
             .catch(error => {
                 console.error('Profile load error:', error);
                 const data = {
                     error: "Load profile failed."
                 };

                 templateBuilder.append("error", data, "errors")
             })
    }

    updateProfile(profile)
    {
        const url = `${config.baseUrl}/profile`;

        axios.put(url, profile)
             .then(() => {
                 const data = {
                     message: "The profile has been updated."
                 };

                 templateBuilder.append("message", data, "errors")
             })
             .catch(error => {
                 console.error('Profile update error:', error);
                 const data = {
                     error: "Save profile failed."
                 };

                 templateBuilder.append("error", data, "errors")
             })
    }
}

document.addEventListener("DOMContentLoaded", () => {
   profileService = new ProfileService();
});
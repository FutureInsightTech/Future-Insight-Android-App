# Banner
Write-Host @"
                                            
                        ███████╗██╗   ██╗████████╗██╗   ██╗██████╗ ███████╗    ██╗███╗   ██╗███████╗██╗ ██████╗ ██╗  ██╗████████╗                                   
                        ██╔════╝██║   ██║╚══██╔══╝██║   ██║██╔══██╗██╔════╝    ██║████╗  ██║██╔════╝██║██╔════╝ ██║  ██║╚══██╔══╝                                   
                        █████╗  ██║   ██║   ██║   ██║   ██║██████╔╝█████╗      ██║██╔██╗ ██║███████╗██║██║  ███╗███████║   ██║                                      
                        ██╔══╝  ██║   ██║   ██║   ██║   ██║██╔══██╗██╔══╝      ██║██║╚██╗██║╚════██║██║██║   ██║██╔══██║   ██║                                      
                        ██║     ╚██████╔╝   ██║   ╚██████╔╝██║  ██║███████╗    ██║██║ ╚████║███████║██║╚██████╔╝██║  ██║   ██║                                      
                        ╚═╝      ╚═════╝    ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝    ╚═╝╚═╝  ╚═══╝╚══════╝╚═╝ ╚═════╝ ╚═╝  ╚═╝   ╚═╝                                      
                                                                                                                                            
             █████╗ ███╗   ██╗██████╗ ██████╗  ██████╗ ██╗██████╗      █████╗ ██████╗ ██████╗ ██╗     ██╗ ██████╗ █████╗ ████████╗██╗ ██████╗ ███╗   ██╗
            ██╔══██╗████╗  ██║██╔══██╗██╔══██╗██╔═══██╗██║██╔══██╗    ██╔══██╗██╔══██╗██╔══██╗██║     ██║██╔════╝██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║
            ███████║██╔██╗ ██║██║  ██║██████╔╝██║   ██║██║██║  ██║    ███████║██████╔╝██████╔╝██║     ██║██║     ███████║   ██║   ██║██║   ██║██╔██╗ ██║
            ██╔══██║██║╚██╗██║██║  ██║██╔══██╗██║   ██║██║██║  ██║    ██╔══██║██╔═══╝ ██╔═══╝ ██║     ██║██║     ██╔══██║   ██║   ██║██║   ██║██║╚██╗██║
            ██║  ██║██║ ╚████║██████╔╝██║  ██║╚██████╔╝██║██████╔╝    ██║  ██║██║     ██║     ███████╗██║╚██████╗██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║
            ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚═╝╚═════╝     ╚═╝  ╚═╝╚═╝     ╚═╝     ╚══════╝╚═╝ ╚═════╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝
                                                                                                                                            
                                    "Unlocking the Future, One Insight at a Time"                                                                                      
"@

# Section: System Check
Write-Host "=== System Check ==="
Write-Host "Checking system status..."

# Section: Commit Message
Write-Host "=== Commit Message ==="

# Function to validate if a commit message is provided
function Validate-CommitMessage {
    $message = Read-Host "✏️ Enter your commit message: "
    if (-not $message) {
        Write-Host "Commit message cannot be empty. Please enter a commit message."
        return $false
    }
    else {
        return $true
    }
}

# Prompt the user for a commit message until a non-empty message is entered
while (-not (Validate-CommitMessage)) {
    continue
}

Write-Host

# Section: Git Add
Write-Host "=== Git Add ==="
git add .
$add_exit_code = $LASTEXITCODE

if ($add_exit_code -ne 0) {
    git add . > add.log
}

Write-Host

# Section: Check Git Add Success
Write-Host "=== Check Git Add Success ==="
if ($add_exit_code -eq 0) {
    Write-Host "Git add successful! ✅"
    Remove-Item -Path add.log -Force
}
else {
    Write-Host "Git add failed. Please check your changes and try again. ❌"
    Write-Host "Errors encountered during git add. Please check add.log for more details."
}

Write-Host

# Section: Git Commit
Write-Host "=== Git Commit ==="
git commit -m "$message"
$commit_exit_code = $LASTEXITCODE

if ($commit_exit_code -ne 0) {
    git commit -m "$message" > commit.log
}

Write-Host

# Section: Check Git Commit Success
Write-Host "=== Check Git Commit Success ==="
if ($commit_exit_code -eq 0) {
    Write-Host "Commit successful! 🎉"
    Remove-Item -Path commit.log -Force
}
else {
    Write-Host "Commit failed. Please check your changes and try again. ❌"
    Write-Host "Errors encountered during commit. Please check commit.log for more details."
}

Write-Host

# Section: Push Commit
Write-Host "=== Push Commit ==="
$current_branch = git rev-parse --abbrev-ref HEAD

git push
$push_exit_code = $LASTEXITCODE

if ($push_exit_code -ne 0) {
    git push > push.log
}

Write-Host

# Section: Check Push Commit Success
Write-Host "=== Check Push Commit Success ==="
if ($push_exit_code -eq 0) {
    Write-Host "Commit pushed successfully! 🚀"
    Remove-Item -Path push.log -Force
}
else {
    Write-Host "Push failed. Please check your network connection and try again. ❌"
    Write-Host "Errors encountered during push. Please check push.log for more details."
}

Write-Host

# Section: Push Commit Upstream
Write-Host "=== Push Commit Upstream ==="

git push -u origin $current_branch
$push_upstream_exit_code = $LASTEXITCODE

if ($push_upstream_exit_code -ne 0) {
    git push -u origin $current_branch > push_upstream.log
}

Write-Host

# Section: Check Push Commit Upstream Success
Write-Host "=== Check Push Commit Upstream Success ==="
if ($push_upstream_exit_code -eq 0) {
    Write-Host "Branch published and commit pushed successfully! 🚀"
    Remove-Item -Path push_upstream.log -Force
}
else {
    Write-Host "Push upstream failed. Please check your network connection and try again. ❌"
    Write-Host "Errors encountered during push upstream. Please check push_upstream.log for more details."
}

Write-Host

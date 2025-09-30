# List of VMs (user@ip)
$targets = @(
    "vm1@20.169.172.200",
    "vm2@172.172.139.118",
    "vm3@172.190.252.178"
)

# Local files to copy
$jar = ".\target\vertx-demo-1.0-SNAPSHOT.jar"
$config = ".\cluster.xml"
$installation_script = ".\prepare_the_machine.sh"

# Remote directory (home of each VM user)
$remoteDir = "/home"

foreach ($target in $targets) {
    Write-Host ">>> Deploying to $target"

    # Extract username from target
    $parts = $target.Split("@")
    $user = $parts[0]

    # Build destination path (use ${} to avoid PowerShell parsing issues)
    $dest = "${target}:${remoteDir}/${user}/"

    # Copy files
    #scp $jar $dest
    scp $config $dest
    #scp $installation_script $dest

    Write-Host ">>> Done copying to $target"
}



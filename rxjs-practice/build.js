const { execSync } = require('child_process');

function runCommand(command, description) {
  try {
    console.log(`Running: ${description}`);
    execSync(command, { stdio: 'inherit' });
  } catch (error) {
    console.error('\x1b[31m%s\x1b[0m', `Build failed during: ${description}`);
    process.exit(1);
  }
}

try {
  runCommand('npm run clean', 'Clean');
  runCommand('npm run format', 'Format');
  runCommand('npm run lint', 'Lint');
  runCommand('npm run test', 'Test');
  runCommand('tsc', 'Compile');
  console.log('\x1b[32m%s\x1b[0m', 'Build is successful!');
} catch (error) {
  console.error('\x1b[31m%s\x1b[0m', 'Build failed.');
}

from xmldiff import main
import xmldiff
import sys
from colorama import init, Fore

succes_text = 'SUCCESS'
failed_text = 'FAILED'

def filterWrongOrderDiffs(diff):
    new_diff = []
    for elem in diff:
        if not isinstance(elem, xmldiff.actions.MoveNode) and not isinstance(elem, xmldiff.actions.RenameNode):
            new_diff.append(elem)
    
    return new_diff

def diff():
    init()
    expected_output = str(sys.argv[1])
    actual_output = str(sys.argv[2])
    diff = main.diff_files(expected_output, actual_output)
    diff = filterWrongOrderDiffs(diff)

    if(len(diff) == 0):
        print(Fore.GREEN + succes_text)
    else:
        print(Fore.RED + failed_text)
        print(diff)

if __name__ == "__main__":
    diff()


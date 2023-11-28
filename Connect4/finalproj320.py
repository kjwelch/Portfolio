"""Imports"""

import numpy as np
import sys
import math
import random

"""Functionality Functions"""

rows = 6
columns = 7

def create_board():
	board = np.zeros((rows,columns))
	return board

def drop_piece(board, row, col, piece):
	board[row][col] = piece

def is_valid_location(board, col):
	if col >=0 and col <=6:
		return True
	else:
		return False

def full_column(board, col):
	if board[rows-1][col] != 0:
		return True
	else:
		return False

def get_next_open_row(board, col):
	for r in range(rows):
		if board[r][col] == 0:
			return r

def print_board(board, last_move_col=None, turn=None):
    for r in range(rows - 1, -1, -1):
        for c in range(columns):
            piece = int(board[r][c])
            if piece == 1:
                print("\033[1;91mO\033[0m", end=" ")  # Red piece for Player 1
            elif piece == 2:
                print("\033[1;33mO\033[0m", end=" ")  # Gold piece for Player 2
            else:
                print("O", end=" ")  # Empty space

        print()

    if last_move_col is not None and turn is not None:
        for i in range(columns):
            if i == last_move_col:
                arrow_color = "\033[91m" if turn == 0 else "\033[93m"
                print(f"{arrow_color}\u2191\033[0m", end=" ")  # Arrow to indicate the last move column
            else:
                print(" ", end=" ")

        print()

    print("-----------------------------")

def get_valid_moves(board):
	valid_moves = []
	for column in range(7):
		if not full_column(board, column):
			valid_moves.append(column)
	return valid_moves

def is_game_over(board):
	if winning_move(board, 1):
		return True  # Game is over, player 1 wins
	elif winning_move(board, 2):
		return True  # Game is over, player 2 wins
	else:
		return False  # Game is not over


def is_winner(board, player):
	if winning_move(board, player):
		return True
	else:
		return False

def winning_move(board, piece):
	# Check horizontal locations for win
	for c in range(columns-3):
		for r in range(rows):
			if board[r][c] == piece and board[r][c+1] == piece and board[r][c+2] == piece and board[r][c+3] == piece:
				return True

	# Check vertical locations for win
	for c in range(columns):
		for r in range(rows-3):
			if board[r][c] == piece and board[r+1][c] == piece and board[r+2][c] == piece and board[r+3][c] == piece:
				return True

	# Check positively sloped diaganols
	for c in range(columns-3):
		for r in range(rows-3):
			if board[r][c] == piece and board[r+1][c+1] == piece and board[r+2][c+2] == piece and board[r+3][c+3] == piece:
				return True

	# Check negatively sloped diaganols
	for c in range(columns-3):
		for r in range(3, rows):
			if board[r][c] == piece and board[r-1][c+1] == piece and board[r-2][c+2] == piece and board[r-3][c+3] == piece:
				return True

"""Easy Mode Evaluations"""

#EASY MODE

def evaluate_board_heuristic(board, player):
    max_connection_length = 0

    # Evaluate horizontal connections
    max_connection_length = max(max_connection_length, get_max_connection(board, player, 1, 0))

    # Evaluate vertical connections
    max_connection_length = max(max_connection_length, get_max_connection(board, player, 0, 1))

    # Evaluate diagonal connections
    max_connection_length = max(max_connection_length, get_max_connection(board, player, 1, 1))
    max_connection_length = max(max_connection_length, get_max_connection(board, player, 1, -1))

    return max_connection_length


def get_max_connection(board, player, delta_row, delta_col):

    max_length = 0

    for row in range(rows):
        for col in range(columns):
            if board[row][col] == player:
                length = count_connection_length(board, player, row, col, delta_row, delta_col)
                max_length = max(max_length, length)

    return max_length


def count_connection_length(board, player, start_row, start_col, delta_row, delta_col):
    # Count the length of the connection in a given direction starting from a specific position.

    length = 0
    row, col = start_row, start_col

    while 0 <= row < rows and 0 <= col < columns and board[row][col] == player:
        length += 1
        row += delta_row
        col += delta_col

    return length

"""Medium Mode Evaluations"""

#MEDIUM MODE

def evaluate_board_defensive(board, player):
    max_opponent_connection_length = 0

    # Evaluate opponent's horizontal connections
    max_opponent_connection_length = max(max_opponent_connection_length, get_max_connection(board, switch_player(player), 1, 0))

    # Evaluate opponent's vertical connections
    max_opponent_connection_length = max(max_opponent_connection_length, get_max_connection(board, switch_player(player), 0, 1))

    # Evaluate opponent's diagonal connections
    max_opponent_connection_length = max(max_opponent_connection_length, get_max_connection(board, switch_player(player), 1, 1))
    max_opponent_connection_length = max(max_opponent_connection_length, get_max_connection(board, switch_player(player), 1, -1))

    return max_opponent_connection_length

# Helper function to switch the player
def switch_player(player):
    return 1 if player == 2 else 2

"""Hard Mode Evaluations"""

#HARD MODE

def evaluate_board(board, player):
    if is_winner(board, player):
        return 1000  # Winning move

    opponent = switch_player(player)
    if is_winner(board, opponent):
        return -1000  # Opponent winning move

    player_score = evaluate_player(board, player)
    opponent_score = evaluate_player(board, opponent)

    return player_score - opponent_score

def evaluate_player(board, player):
    score = 0

    # Evaluate horizontal connections
    score += count_connections(board, player, 1, 0)

    # Evaluate vertical connections
    score += count_connections(board, player, 0, 1)

    # Evaluate diagonal connections
    score += count_connections(board, player, 1, 1) # Pos
    score += count_connections(board, player, 1, -1) # Neg

    return score

def count_connections(board, player, delta_row, delta_col):

    count = 0

    for row in range(rows):
        for col in range(columns):
            if board[row][col] == player:
                for i in range(1, 4):
                    new_row = row + i * delta_row
                    new_col = col + i * delta_col

                    if 0 <= new_row < rows and 0 <= new_col < columns:
                        if board[new_row][new_col] == player:
                            count += 1
                        else:
                            break
                    else:
                        break

    return count

def switch_player(player):
    return 1 if player == 2 else 2

def make_move(board, col, player):
    row = get_next_open_row(board, col)
    drop_piece(board, row, col, player)
    return board


def minimax(board, depth, maximizing_player, player):
    if depth == 0 or is_game_over(board):
        return evaluate_board(board, player)

    valid_moves = get_valid_moves(board)

    if maximizing_player:
        max_eval = float('-inf')
        for col in valid_moves:
            new_board = make_move(board.copy(), col, player)  # Create a copy of the board
            eval = minimax(new_board, depth - 1, False, player)
            max_eval = max(max_eval, eval)
        return max_eval
    else:
        min_eval = float('inf')
        for col in valid_moves:
            new_board = make_move(board.copy(), col, switch_player(player))  # Create a copy of the board
            eval = minimax(new_board, depth - 1, True, player)
            min_eval = min(min_eval, eval)
        return min_eval


def find_best_move(board, player):
  # Find the best move using the Minimax algorithm

  valid_moves = get_valid_moves(board)
  best_move = None
  best_eval = float('-inf')

  for move in valid_moves:
      new_board = make_move(board.copy(), move, player)  # Create a copy of the board
      eval = minimax(new_board, depth=3, maximizing_player=False, player=player)

      if eval > best_eval or len(valid_moves)==1:
        best_eval = eval
        best_move = move

  return best_move

"""Player Types"""

class Player:
  def __init__(self, board):
    self.difficulty = difficulty
    self.board = board


class EasyPlayer(Player):
  def __init__(self, board, color):
    self.board = board
    self.player = color
    self.difficulty = 1
    easy_pokemons = ['Bulbasaur','Charmander','Squirtle', 'Smeargle','Jigglypuff','Pikachu','Magikarp']
    self.name = random.choice(easy_pokemons)

  def move(self,board):
    heuristic_scores = [evaluate_board_heuristic(board, self.player) if is_valid_location(board, col) else -1 for col in range(columns)]
    valid_columns = [col for col, score in enumerate(heuristic_scores) if score != -1]

    if valid_columns:
        col = np.random.choice(valid_columns)
    else:
        print("No valid moves based on heuristic. Choosing a random column.")
        col = np.random.choice([col for col in range(columns) if is_valid_location(board, col)])
    return col


class MediumPlayer(Player):
  def __init__(self, board, color):
    self.player = color
    self.board = board
    self.difficulty = 2
    med_pokemons = ['Venusaur', 'Charizard', 'Blastoise','Lickilicky', 'Rampardos','Psyduck','Snorlax' ]
    self.name = random.choice(med_pokemons)

  def easy_subroutine(self, board):
    heuristic_scores = [evaluate_board_heuristic(board, self.player) if is_valid_location(board, col) else -1 for col in range(columns)]
    valid_columns = [col for col, score in enumerate(heuristic_scores) if score != -1]

    if valid_columns:
        col = random.choice([max(valid_columns), np.random.choice(valid_columns)])
    else:
        print("No valid moves based on heuristic. Choosing a random column.")
        col = np.random.choice([col for col in range(columns) if is_valid_location(board, col)])
    return col

  def medium_subroutine(self, board):
    opponent = switch_player(self.player)
    for col in get_valid_moves(board):
        new_board = make_move(board.copy(), col, opponent)
        opponent_connections = evaluate_board_defensive(new_board, opponent)

        if opponent_connections >= 2:
            return col

    return self.easy_subroutine(board)

  def move(self,board):
    return self.medium_subroutine(board)


class HardPlayer(Player):
  def __init__(self, board, color):
    self.player = color
    self.board = board
    self.difficulty = 3
    hard_pokemons = ['Rayquaza','Mewtwo','Dialga','Darkrai','Regigigas','Articuno','Zapdos','Entei','Raiku']
    self.name = random.choice(hard_pokemons)


  def move(self, board):
    valid_columns = get_valid_moves(board)
    col = find_best_move(board, self.player)
    return col

class HumanPlayer(Player):
  def __init__(self, board, color):
    self.player = color
    self.board = board
    self.difficulty = 0
    self.name = input('What is your name?: ')

  def move(self,board):
    col = (int(input(f'Select a column 1-{columns}: column '))) - 1
    while not (0 <= col < columns) or not is_valid_location(board, col):
      col = (int(input(f'Invalid input. Select a valid column 1-{columns}'))) - 1
    return col

"""Game Functionality"""

def run_game(p1,p2):
  board = create_board()
  print_board(board)
  game_over = False
  turn = 0

  while not game_over:

    if len(get_valid_moves(board)) ==0:
      game_over = True
      print("TIE!")
      break

    elif turn == 0:
        print(p1.name)
        col = p1.move(board)
        while not is_valid_location(board, col)	or full_column(board, col):
          col = p1.move(board)

    else:
        print(p2.name)
        col = p2.move(board)
        while not is_valid_location(board, col)	or full_column(board, col):
          col = p2.move(board)

    # Drop the piece
    row = get_next_open_row(board, col)
    drop_piece(board, row, col, 1 if turn == 0 else 2)

    # Check for a winning move
    if winning_move(board, 1 if turn == 0 else 2):
        if turn == 0:
          print(p1.name + ' wins!')
        else:
          print(p2.name + ' wins!')
        game_over = True

    print_board(board, last_move_col=col, turn=turn)

    # Switch turns
    turn = (turn + 1) % 2

    if game_over:
        print('---------------------------------')

"""Play Game!"""

game_mode = (input('select a game mode - 1 for play person, 2 for play AI, 3 for spectate: '))

def decide_dif(difficulty, color):
  if difficulty == 1:
    return EasyPlayer(create_board(), color)
  elif difficulty == 2:
    return MediumPlayer(create_board(), color)
  elif difficulty == 3:
    return HardPlayer(create_board(), color)
  elif difficulty == 0:
    return HumanPlayer(create_board(), color)

if game_mode == '3':
  # Player 1 (AI)
  difficulty = int(input('select a difficulty for the AI - 1 for easy, 2 for medium or 3 for hard:'))
  color = 1
  p1 = decide_dif(difficulty, color)

  # Player 2 (AI)
  difficulty2 = int(input('select a difficulty for the AI - 1 for easy, 2 for medium or 3 for hard:'))
  color = 2
  p2 = decide_dif(difficulty2, color)

  run_game(p1,p2)

elif game_mode == '2':
  # Player 2 (Human)
  difficulty2 = 0


  # Player 2 (AI)
  difficulty = int(input('select a difficulty for the AI - 1 for easy, 2 for medium or 3 for hard:'))
  color = int(input('What color do you want to be - 1 for red, 2 for yellow: '))
  color2 = 2 if color == 1 else 1

  p1 = decide_dif(difficulty2, color)
  p2 = decide_dif(difficulty, color2)

  run_game(p1,p2) if color == 1 else run_game(p2,p1)



elif game_mode == '1':
  # Player 2 (Human)
  difficulty = 0
  color = int(input('What color do you want to be - 1 for red, 2 for yellow: '))
  p1 = decide_dif(difficulty, color)


  # Player 2 (Human)
  difficulty2 = 0
  color2 = 2 if color == 1 else 1
  p2 = decide_dif(difficulty2, color2)

  run_game(p1,p2)
else:
  game_mode = (input('Invalid input. Select a valid game mode - spectate, play AI or play person:'))
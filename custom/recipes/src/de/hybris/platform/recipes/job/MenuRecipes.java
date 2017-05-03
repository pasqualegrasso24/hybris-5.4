
package de.hybris.platform.recipes.job;

import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.jalo.SearchResult;
import de.hybris.platform.recipes.model.RecipeModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;

import org.apache.log4j.Logger;




public class MenuRecipes extends AbstractJobPerformable<CronJobModel>
{


	private static final Logger LOG = Logger.getLogger(MenuRecipes.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.
	 * CronJobModel)
	 */
	@Override
	public PerformResult perform(final CronJobModel arg0)
	{
		final String fsq = "SELECT {PK} FROM {Recipe}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(fsq);
		final SearchResult<RecipeModel> result = (SearchResult<RecipeModel>) flexibleSearchService.search(query);
		final List<RecipeModel> recipes = result.getResult();

		if (recipes.isEmpty())
		{
			LOG.info("Non c'è nessuna ricetta");
		}
		return null;
	}


	/*
	 * private PlayerService playerService; private MailService mailService;
	 *
	 * @Override public PerformResult perform(final CronJobModel cronJob) { LOG.info("sto inviando email");
	 *
	 * final List<RankingData> rankings = playerService.getRankings();
	 *
	 * if (rankings.isEmpty()) { LOG.info("No competitions have changed....."); return new
	 * PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED); }
	 *
	 * for (final PlayerModel player : playerService.getAllPlayers()) { final List<RankingData> playerRankings =
	 * playerService.filterRankingsForPlayer(rankings, player); if (!playerRankings.isEmpty() &&
	 * player.isSendNewsletter()) { //mailService.sendRankingMail(player, playerRankings); LOG.info("email inviata"); } }
	 *
	 * return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED); }
	 *
	 * public void setPlayerService(final PlayerService playerService) { this.playerService = playerService;
	 *
	 * }
	 *
	 * public void setMailService(final MailService trailMailService) { this.mailService = trailMailService; }
	 */
}


package org.bigram.docvalidator.config;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for creating Configuration object with the Builder.
 */
public class ConfigurationBuilderTest {
  @Test
  public void testBuildSimpleConfiguration() {
    Configuration config = new Configuration.Builder()
        .addSectionValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
        .addSentenceValidatorConfig(new ValidatorConfiguration("SentenceLength"))
        .setCharacterTable(new CharacterTable()).build();

    assertEquals(1, config.getSentenceValidatorConfigs().size());
    assertEquals(1, config.getSectionValidatorConfigs().size());
    assertEquals(0, config.getParagraphValidatorConfigs().size());
    assertEquals(0, config.getDocumentValidatorConfigs().size());
    assertNotNull(config.getCharacterTable());
    assertEquals("InvalidExpression", config.getSectionValidatorConfigs()
        .get(0).getConfigurationName());
    assertEquals("SentenceLength", config.getSentenceValidatorConfigs()
        .get(0).getConfigurationName());
  }

  @Test
  public void testBuildConfigurationWithoutCharacterTable() {
    Configuration config = new Configuration.Builder()
        .addSectionValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
        .addSentenceValidatorConfig(new ValidatorConfiguration("SentenceLength")).build();

    assertEquals(1, config.getSentenceValidatorConfigs().size());
    assertEquals(1, config.getSectionValidatorConfigs().size());
    assertEquals(0, config.getParagraphValidatorConfigs().size());
    assertEquals(0, config.getDocumentValidatorConfigs().size());
    assertNull(config.getCharacterTable());
    assertEquals("InvalidExpression", config.getSectionValidatorConfigs()
        .get(0).getConfigurationName());
    assertEquals("SentenceLength", config.getSentenceValidatorConfigs()
        .get(0).getConfigurationName());
  }

  @Test
  public void testBuildConfigurationAddingProperties() {
    Configuration config = new Configuration.Builder()
        .addSectionValidatorConfig(new ValidatorConfiguration("InvalidExpression")
            .addAttribute("dict", "./foobar.dict"))
        .addSentenceValidatorConfig(new ValidatorConfiguration("SentenceLength")
                .addAttribute("max_length", "10")).build();

    assertEquals(1, config.getSentenceValidatorConfigs().size());
    assertEquals(1, config.getSectionValidatorConfigs().size());
    assertEquals(0, config.getParagraphValidatorConfigs().size());
    assertEquals(0, config.getDocumentValidatorConfigs().size());
    assertNull(config.getCharacterTable());
    assertEquals("InvalidExpression", config.getSectionValidatorConfigs()
        .get(0).getConfigurationName());
    assertEquals("./foobar.dict",
        config.getSectionValidatorConfigs().get(0).getAttribute("dict"));
    assertEquals("SentenceLength", config.getSentenceValidatorConfigs()
        .get(0).getConfigurationName());
    assertEquals("10",
        config.getSentenceValidatorConfigs().get(0).getAttribute("max_length"));
  }
}
